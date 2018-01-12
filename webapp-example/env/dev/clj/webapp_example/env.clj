(ns webapp-example.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [webapp-example.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[webapp-example started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[webapp-example has shut down successfully]=-"))
   :middleware wrap-dev})
