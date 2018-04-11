(ns state-initializer)

(defn counter? [rule] (= (nth rule 0) 'define-counter))
(defn signal? [rule] (= (nth rule 0) 'define-signal))


(defn rule-to-counter [rule]  {:type "counter"
                               :name (nth rule 1)
                               :params (nth rule 2)
                               :condition (nth rule 3)
                               :truth-table []
                               })

(defn rule-to-signal [rule] {:type "signal"
                             :name (keys (second rule))
                             :operation (first (vals (nth rule 1)))
                             :condition (nth rule 2)
                             })

(defn transform-to-state-row [rule]
      (if (signal? rule) (rule-to-signal rule) (rule-to-counter rule)))

(defn get-state [rules] {:rules (map transform-to-state-row rules)
                        :past-data [{}]})
