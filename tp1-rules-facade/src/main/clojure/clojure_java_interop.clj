(ns clojure-java-interop (:require [data-processor :refer :all]
                                   [clojure.data.json :as json]
                                   [clojure.walk :as walk])
                         (:gen-class))

(def rules '((define-counter "email-count" []
                             true)
              (define-signal {"spam-fraction" (/ (counter-value "spam-count" [])
                                                 (counter-value "email-count" []))}
                             true)
              ))

(defn -initialize []
  (json/write-str (initialize-processor rules)))

(defn -process-data [state new-data]
  (do (println (json/read-str state))
      (json/write-str (first (process-data (walk/keywordize-keys (json/read-str state)) (json/read-str new-data))) ) )
  )

(defn -add-rule [state new-rules]
  (json/write-str (add-rule state (json/read-str new-rules) )))

