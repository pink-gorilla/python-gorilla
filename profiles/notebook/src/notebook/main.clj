(ns notebook.main
  (:require
   [pinkgorilla.embedded :refer [#_run-notebook start-notebook]]
   [pinkgorilla.python.plot]) ;; bring to scope 
  (:gen-class))

(defn start []
  (start-notebook "./profiles/notebook/config.edn"))

(defn -main []
  (println "Running PinkGorilla Notebook")
  (start))

(comment
  (start)

  ;comment end
  )