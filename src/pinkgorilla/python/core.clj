(ns pinkgorilla.python.core
  (:require
   [libpython-clj.python :as py]
   [pinkgorilla.notebook-app.system]))

(defn py-initialize! []
  (let [config (pinkgorilla.notebook-app.system/get-setting [:python])]
    (println "python config: " config)
    (py/initialize!
     :python-executable (:python-executable config)
     :library-path (:library-path config))))