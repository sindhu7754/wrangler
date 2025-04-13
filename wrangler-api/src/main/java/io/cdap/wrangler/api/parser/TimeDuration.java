
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// package io.cdap.wrangler.api.Token;
package io.cdap.wrangler.api.parser;

public interface Token {
    String type();
    String value();
    String toJson();
}


public class TimeDuration extends Token {
  private final long milliseconds;

  public TimeDuration(String value) {
    super(value);
    this.milliseconds = parseTime(value);
  }

  private long parseTime(String value) {
    value = value.toLowerCase();
    if (value.endsWith("ms")) {
      return (long)(Double.parseDouble(value.replace("ms", "")));
    } else if (value.endsWith("s")) {
      return (long)(Double.parseDouble(value.replace("s", "")) * 1000);
    } else if (value.endsWith("m")) {
      return (long)(Double.parseDouble(value.replace("m", "")) * 60 * 1000);
    } else if (value.endsWith("h")) {
      return (long)(Double.parseDouble(value.replace("h", "")) * 60 * 60 * 1000);
    } else if (value.endsWith("d")) {
      return (long)(Double.parseDouble(value.replace("d", "")) * 24 * 60 * 60 * 1000);
    } else {
      throw new IllegalArgumentException("Unknown time unit in: " + value);
    }
  }

  public long getMilliseconds() {
    return milliseconds;
  }
}
