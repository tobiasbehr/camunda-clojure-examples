(defproject simple-process "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.camunda.bpm/camunda-engine "7.8.0"]
                 [com.h2database/h2 "1.4.196"]
                 [camunda-clojure/camunda-clojure "0.1.0"]]
  :main ^:skip-aot simple-process.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
