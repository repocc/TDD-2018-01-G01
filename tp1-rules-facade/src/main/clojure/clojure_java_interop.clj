(ns clojure-java-interop (:require [data-processor :refer :all]
                                   [clojure.data.json :as json]
                                   [clojure.walk :as walk])
                         (:gen-class))

;;default values.
(def rules '(
              (define-counter "spam-important-table" [(current "spam")
                                                      (current "important")]
                              true)
              ))

(defn -initialize []
  (json/write-str (initialize-processor rules)))

(defn write-json [state new-data])

(defn -process-data [state new-data]
  (do (println "Recived data " (json/read-str new-data))
      [(json/write-str (first (process-data (walk/keywordize-keys (json/read-str state)) (json/read-str new-data))))
       (json/write-str (second (process-data (walk/keywordize-keys (json/read-str state)) (json/read-str new-data))))]  )
  )

(defn -add-rule [state new-rules]
  (json/write-str (add-rule (walk/keywordize-keys (json/read-str state)) (json/read-str new-rules) )))