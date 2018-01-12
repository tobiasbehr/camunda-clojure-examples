(ns webapp-example.routes.home
  (:require [webapp-example.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [webapp-example.camunda :as camunda]))

(defn home-page []
  (let [process-definitions (camunda/all-process-definitions)]
    (layout/render
     "home.html" {:engine camunda/engine :process-definitions process-definitions :tasks (camunda/all-tasks)})))

(defn about-page []
  (layout/render "about.html"))

(defn handle-post [action {params :form-params}]
  (condp = action
    "start-process" (camunda/start-process! (get params "process-definition-key") (get params "business-key"))
    "complete-task" (camunda/complete-task! (get params "task-id"))))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/" [action :as request] (handle-post action request) (home-page))
  (GET "/about" [] (about-page)))

