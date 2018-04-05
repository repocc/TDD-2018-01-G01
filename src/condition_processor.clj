(ns condition-processor (:use [clojure.string :as string]))

;;todo define a global translator for the DSL
(defn apply-operation [identifier args]
      (cond
        (= identifier '=) (= (first args) (second args))
        (= identifier 'and) (and (first args) (second args))
        (= identifier 'or) (or (first args) (second args))
        (= identifier '!=) (not= (first args) (second args))
        (= identifier 'includes?) (string/includes? (first args) (second args))))

(defn current? [value] (= (nth value 0) 'current ))
(defn past? [value] (= (nth value 0) 'past ))

(defn pass? [condition data past-data]
      "Given a current data and a past data, determines if the given condition is true"
      (cond
        (current? condition) (get data (second condition))
        (past? condition) (get past-data (second condition))
        :else
        (apply-operation (first condition) (map #(pass? % data past-data) (rest condition)))))

;; return true if the condition is met
;; data is the current data being processed
;; past-data is collection of data previously processed
;; see the manual for the correctness of the condition expression
(defn pass-condition? [condition data past-data]
      (some #(pass? condition data %) past-data))
