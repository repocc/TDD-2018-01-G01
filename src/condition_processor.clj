(ns condition-processor (:require [clojure.string :as string]
                              [dsl-condition]))

(defn current? [value] (= (nth value 0) 'current ))
(defn past? [value] (= (nth value 0) 'past ))


(defmulti eval-condition (fn [condition data past-data]
                           [(type condition)]))

(defmethod eval-condition [java.lang.Number] [condition data past-data]
  condition)

(defmethod eval-condition [java.lang.String] [condition data past-data]
  condition)

(defmethod eval-condition [java.lang.Boolean] [condition data past-data]
  condition)

(defmethod eval-condition :default [condition data past-data]
  (cond
    (current? condition) (get data (second condition))
    (past? condition) (get past-data (second condition))
    :else (dsl-condition/apply-operation (first condition) (map #(eval-condition % data past-data) (rest condition)))))


(defn maybe-pass? [condition data past-data]
  "Calls pass? to see if a condition is met. Return false in case of exception"
  (try
    (eval-condition condition data past-data)
    (catch Exception e false)))

;; return true if the condition is met
;; data is the current data being processed
;; past-data is collection of data previously processed
;; see the manual for the correctness of the condition expression
(defn pass-condition? [condition data past-data]
  (not (nil? (some #(maybe-pass? condition data %) past-data))))

