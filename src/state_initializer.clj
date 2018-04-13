(ns state-initializer  (:require [condition-processor :refer :all]))


(defn counter? [rule] (= (nth rule 0) 'define-counter))
(defn signal? [rule] (= (nth rule 0) 'define-signal))

(defn rule-to-normal-counter [rule] {:type "counter"
                                     :name (nth rule 1)
                                     :params (nth rule 2)
                                     :condition (nth rule 3)
                                     :truth-table []
                                     :step 1
                                     })

(defn rule-to-stepped-counter [rule] {:type "counter"
                                      :name (nth rule 1)
                                      :params (nth rule 3)
                                      :condition (nth rule 4)
                                      :truth-table []
                                      :step (nth rule 2)
                                      })


(defn rule-to-counter [rule]  (if(counter? rule) (rule-to-normal-counter rule) (rule-to-stepped-counter rule)) )




(defn rule-to-signal [rule] {:type "signal"
                             :name (first (keys (second rule)))
                             :operation (first (vals (nth rule 1)))
                             :condition (nth rule 2)
                             })


;; hacer un defmulti para evaluar
(defn transform-to-state-row [rule]
      (if (signal? rule) (rule-to-signal rule) (rule-to-counter rule)))

(defn get-state [rules]  {:rules (map transform-to-state-row rules)
                        :past-data [{}]})
