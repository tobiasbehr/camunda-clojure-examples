(ns user
  (:require 
            [mount.core :as mount]
            webapp-example.core))

(defn start []
  (mount/start-without #'webapp-example.core/repl-server))

(defn stop []
  (mount/stop-except #'webapp-example.core/repl-server))

(defn restart []
  (stop)
  (start))


