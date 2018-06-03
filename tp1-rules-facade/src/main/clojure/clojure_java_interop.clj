(ns clojure-java-interop (:require [data-processor :refer :all]
                                   [clojure.data.json :as json]
                                   [clojure.walk :as walk])
                         (:gen-class))

(def rules '(
              (define-counter "string-test" [] (= (current "name") "John"))
              ))

(defn -initialize []
  (json/write-str (initialize-processor rules)))

(defn -process-data [state new-data]
  (do (println (json/read-str new-data))
      (json/write-str (first (process-data (walk/keywordize-keys (json/read-str state)) (json/read-str new-data))) ) )
  )

(defn -add-rule [state new-rules]
  (json/write-str (add-rule (walk/keywordize-keys (json/read-str state)) (json/read-str new-rules) )))