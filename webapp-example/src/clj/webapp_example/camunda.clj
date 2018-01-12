(ns webapp-example.camunda
  (:require [camunda-clojure.engine :as engine]
            [camunda-clojure.runtime :as runtime]
            [camunda-clojure.repository :as repository]
            [camunda-clojure.tasks :as tasks]))

(def engine (engine/create-engine {:jdbc-url "jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000"
                                   :db-schema-update "create-drop"
                                   :job-executor-activate true}))

(def deployment (repository/deploy! engine "Deployment 1" ["processes/Process_2.bpmn"]))

(defn all-deployments []
  [deployment])

(defn- add-business-key [task]
  (let [process-instance-id (:process-instance-id task)
        process-instance (first (runtime/find-process-instances engine {:process-instance-id process-instance-id}))]
    (assoc task :business-key (:business-key process-instance))))

(defn all-tasks []
  (map add-business-key (tasks/find-tasks engine)))

(defn all-process-definitions []
  (repository/get-process-definitions engine))

(defn start-process! [process-definition-key business-key]
  (runtime/start-process! engine process-definition-key business-key))

(defn complete-task! [task-id]
  (tasks/complete! engine {:id task-id}))
