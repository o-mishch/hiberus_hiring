package com.hiberus.hiring.utils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import com.hiberus.hiring.controller.dto.OfferByPartNumber;
import com.hiberus.hiring.entity.Offer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimetableFlattenUtils {

	public List<OfferByPartNumber> getOfferByPartNumber(List<Offer> offers) {
		if (offers.isEmpty()) {
			return Collections.emptyList();
		}

		List<Event> events = createSortedEvents(offers);
		return processEvents(events);
	}

	private List<Event> createSortedEvents(List<Offer> offers) {
		return offers.stream()
				.flatMap(offer -> Stream.of(
						new Event(offer.getStartDate(), EventType.START, offer),
						new Event(offer.getEndDate(), EventType.END, offer)
				))
				.sorted(Comparator.comparing(Event::time)
						.thenComparing(Event::type)
						.thenComparing(e -> e.offer.getPriority(), Comparator.reverseOrder()))
				.toList();
	}

	private List<OfferByPartNumber> processEvents(List<Event> events) {
		PriorityQueue<Offer> activeOffers = new PriorityQueue<>(
				Comparator.comparing(Offer::getPriority, Comparator.reverseOrder())
		);
		List<OfferByPartNumber> result = new ArrayList<>();
		AtomicReference<Instant> currentTime = new AtomicReference<>(events.getFirst().time());

		events.forEach(event -> {
			updateResult(activeOffers, result, currentTime.get(), event.time().minusMillis(1));
			handleEvent(activeOffers, event);
			currentTime.set(event.time());
		});
		return result;
	}

	private void updateResult(PriorityQueue<Offer> activeOffers, List<OfferByPartNumber> result,
			Instant startTime, Instant endTime) {
		if (activeOffers.isEmpty()) {
			return;
		}

		Offer topOffer = activeOffers.peek();
		if (!result.isEmpty()
				&& result.getLast().getPrice().equals(topOffer.getPrice())
				&& result.getLast().getCurrencyIso().equals(topOffer.getCurrency().getIsoCode())) {
			result.getLast().setEndDate(endTime.toString());
		} else {
			result.add(new OfferByPartNumber(
					startTime.toString(), endTime.toString(),
					topOffer.getPrice(), topOffer.getCurrency().getIsoCode()
			));
		}
	}

	private void handleEvent(PriorityQueue<Offer> activeOffers, Event event) {
		if (event.type() == EventType.START) {
			activeOffers.add(event.offer);
		} else {
			activeOffers.remove(event.offer);
		}
	}

	private enum EventType {START, END}

	private record Event(Instant time, EventType type, Offer offer) {
	}

	@Deprecated(forRemoval = true)
	public List<OfferByPartNumber> getOfferByPartNumberLegacy(List<Offer> offers) {
		if (offers == null || offers.isEmpty()) {
			return Collections.emptyList();
		}

		offers.sort(Comparator.comparing(Offer::getStartDate)
				.thenComparing(Offer::getPriority, Comparator.reverseOrder())
				.thenComparing(Offer::getEndDate));

		List<OfferByPartNumber> result = new ArrayList<>();
		PriorityQueue<Offer> activeOffers = new PriorityQueue<>(
				Comparator.comparing(Offer::getPriority, Comparator.reverseOrder())
		);

		Instant current = offers.getFirst().getStartDate();
		int index = 0;

		while (index < offers.size() || !activeOffers.isEmpty()) {
			while (index < offers.size() && !offers.get(index).getStartDate().isAfter(current)) {
				activeOffers.offer(offers.get(index));
				index++;
			}

			if (activeOffers.isEmpty()) {
				if (index < offers.size()) {
					current = offers.get(index).getStartDate();
				}
				continue;
			}

			Offer topOffer = activeOffers.peek();
			Instant nextChange = topOffer.getEndDate();

			if (index < offers.size()) {
				nextChange = nextChange.isBefore(offers.get(index).getStartDate()) ? nextChange : offers.get(index).getStartDate();
			}

			OfferByPartNumber offerEntry = new OfferByPartNumber(
					current.toString(),
					nextChange.minusMillis(1).toString(),
					topOffer.getPrice(),
					topOffer.getCurrency().getIsoCode()
			);

			if (result.isEmpty() || !result.getLast().equals(offerEntry)) {
				result.add(offerEntry);
			}

			current = nextChange;
			Instant finalCurrent = current;
			activeOffers.removeIf(offer -> !offer.getEndDate().isAfter(finalCurrent));
		}

		return result;
	}
}
