(defproject test-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [jp.nijohando/event.websocket "0.1.0"]
                 [org.clojure/core.async "1.3.610"]
                 [org.clojure/data.json "1.0.0"]
                 [clj-time "0.5.1"]]
  :repl-options {:init-ns test-clj.core})
