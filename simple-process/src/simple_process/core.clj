(ns simple-process.core
  (:require [camunda-clojure.engine :as engine]
            [camunda-clojure.repository :as repository]
            [camunda-clojure.runtime :as runtime]
            [camunda-clojure.tasks :as tasks])
  (:gen-class))


(compile (symbol "simple-process.delegates"))

(def engine (engine/create-engine {:jdbc-url "jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000"
                                   :db-schema-update "create-drop"
                                   :job-executor-activate true}))

(defn -main
  "Shows basic interaction with the camunda engine"
  [& args]
  (println "Deploying process definitions ...")
  (repository/deploy! engine "MyDeployment" ["Process_1.bpmn" "Process_2.bpmn"])

  (println "Starting process instance of Process_1")
  (runtime/start-process! engine "Process_1" {"var1" "Hallo" "var2" "Welt"})

  (println "Starting process instance of Process_2")
  (runtime/start-process! engine "Process_2")

  (println "Running process instances:\n\t" (runtime/find-process-instances engine))

  (let [task (first (tasks/find-tasks engine))]
    (println "Available task:\n\t" task)
    (println "Claiming the task ...")
    (tasks/claim! engine task "John Doe")

    (println "Completing the task" (:id task) " ..." )
    (tasks/complete! engine task {"approved" true}))

  (println "Running process instances:\n\t" (runtime/find-process-instances engine))
  (println "Available tasks:\n\t" (tasks/find-tasks engine))
  (System/exit 0))
