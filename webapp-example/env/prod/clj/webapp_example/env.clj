(ns webapp-example.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[webapp-example started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[webapp-example has shut down successfully]=-"))
   :middleware identity})
