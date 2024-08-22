/*
 * Copyright (C) 2024 NotEnoughUpdates contributors
 *
 * This file is part of NotEnoughUpdates.
 *
 * NotEnoughUpdates is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * NotEnoughUpdates is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NotEnoughUpdates. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.moulberry.notenoughupdates.miscfeatures;

import lombok.Getter;

public class Clock {
	private long remainingTime;
	@Getter
	private boolean paused;
	@Getter
	private boolean scheduled;
	@Getter
	private long endTime;

	public void schedule(long milliseconds) {
		this.endTime = System.currentTimeMillis() + milliseconds;
		this.remainingTime = milliseconds;
		this.scheduled = true;
		this.paused = false;
	}

	public void schedule(double milliseconds) {
		this.endTime = (System.currentTimeMillis() + (long) milliseconds);
		this.remainingTime = (long) milliseconds;
		this.scheduled = true;
		this.paused = false;
	}

	public long getRemainingTime() {
		if (paused) {
			return remainingTime;
		}
		if (endTime - System.currentTimeMillis() < 0) {
			return 0;
		}
		return endTime - System.currentTimeMillis();
	}

	public boolean passed() {
		return System.currentTimeMillis() >= endTime;
	}

	public void pause() {
		if (scheduled && !paused) {
			remainingTime = endTime - System.currentTimeMillis();
			paused = true;
		}
	}

	public void resume() {
		if (scheduled && paused) {
			endTime = System.currentTimeMillis() + remainingTime;
			paused = false;
		}
	}

	public void reset() {
		scheduled = false;
		paused = false;
		endTime = 0;
		remainingTime = 0;
	}
}
