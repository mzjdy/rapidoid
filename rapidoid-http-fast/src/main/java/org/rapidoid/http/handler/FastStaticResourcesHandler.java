package org.rapidoid.http.handler;

/*
 * #%L
 * rapidoid-http-fast
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
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

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.commons.MediaType;
import org.rapidoid.http.FastHttp;
import org.rapidoid.http.HttpStatus;
import org.rapidoid.http.HttpUtils;
import org.rapidoid.http.Req;
import org.rapidoid.io.Res;
import org.rapidoid.net.abstracts.Channel;
import org.rapidoid.u.U;

@Authors("Nikolche Mihajlovski")
@Since("5.0.0")
public class FastStaticResourcesHandler extends AbstractFastHttpHandler {

	private final FastHttp http;

	public FastStaticResourcesHandler(FastHttp http) {
		super(http, null);
		this.http = http;
	}

	@Override
	public HttpStatus handle(Channel ctx, boolean isKeepAlive, Req req, Object extra) {
		http.getListener().state(this, req);

		try {
			String[] staticFilesLocations = http.getStaticFilesLocations();

			if (!U.isEmpty(staticFilesLocations)) {
				Res res = HttpUtils.staticPage(req, staticFilesLocations);

				byte[] bytes = res.getBytesOrNull();

				if (bytes != null) {
					MediaType contentType = MediaType.getByFileName(res.getName());
					http.getListener().result(this, contentType, bytes);
					http.write200(ctx, isKeepAlive, contentType, bytes);
					return HttpStatus.DONE;
				}
			}

			http.getListener().resultNotFound(this);
			return HttpStatus.NOT_FOUND;

		} catch (Exception e) {
			http.error(ctx, isKeepAlive, req, e);
			return HttpStatus.ERROR;
		}
	}

	@Override
	public boolean needsParams() {
		return true;
	}

}