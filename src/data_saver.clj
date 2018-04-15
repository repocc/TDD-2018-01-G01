(ns data-saver)

;;returns true if value is like (past "something")
(defn past? [value]
  (and (> (count value) 1) (= (nth value 0) 'past) ))

;;returns a list with all the values of past conditions
(defn get-past-conditions [rule]
  (cond
    (symbol? rule) true
    (past? rule) (list (nth rule 1))
    (list? rule) (conj (map #(get-past-conditions %) (rest rule) ))))


(def not-nil? (complement nil?))

(def any? (complement not-any?))

;; returns true if data constains condition
(defn data-has-condition? [data condition]
  (not-nil? (get data condition)))

;;receives the condition of one rule of the current state and returns true if data contains all the past conditions for
;;that rule
;;returns false if the rule doesn't have past conditions, wich means data isn't useful for that rule.
(defn useful-data-for-rule? [data rule]
  (def past-list (filter not-nil? (flatten (get-past-conditions rule))))
  (if (empty? past-list) false (every? true? (map #(data-has-condition? data %) past-list))))
