package org.rapidoid.gui.menu;

/*
 * #%L
 * rapidoid-gui
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.List;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;

@Authors("Nikolche Mihajlovski")
@Since("4.1.0")
public class PageSubMenu {

	private final List<PageSubMenuItem> items;

	public PageSubMenu(List<PageSubMenuItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "AppSubMenu [items=" + items + "]";
	}

	public List<PageSubMenuItem> getItems() {
		return items;
	}

}
