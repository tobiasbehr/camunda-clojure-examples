(ns simple-process.delegates)

;; ----- Delegates -----

(gen-class
 :name simple_process.delegates.DetermineApproval
 :prefix determineApproval-
 :implements [org.camunda.bpm.engine.delegate.JavaDelegate])

(defn determineApproval-execute [this execution]
  (.setVariable execution "needsApproval" true))
