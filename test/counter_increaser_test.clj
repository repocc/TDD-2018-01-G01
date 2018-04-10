(ns counter-increaser-test
  (:require [clojure.test :refer :all]
            [counter-increaser :refer :all]
            [condition-processor :refer :all]
            [state-initializer :refer :all]
            ))

(def truth-table [{:key [true true] :value 1}, {:key [true false] :value 1}])

(def data1 {"spam" true, "important" true})
(def data2 {"spam" false, "important" false})
(def data3 {"spam" false, "important" true})

(def rule {:type "counter" :name "spam-important-table" :params [] :condition true :truth-table truth-table})

(def past-data [data1, data2])

(def rules '((define-counter "email-count" []
               true)
             (define-counter "spam-count" []
               (current "spam"))))

(def rules-mapped (map transform-to-state-row rules))

(def state (get-state rules))

(deftest data-to-table-key-test
  (testing "Mapping data to truth table key"
    (is (= [true true]
           (into [] (map data-to-table-key data1))))))

(deftest key-is-not-present-test
 (testing "Test if key is present or not in truth table"
    (is (= false
          (key-is-not-present? truth-table data1)))
    (is (= true
          (key-is-not-present? truth-table data2)))))

(deftest inc-counter-value-key-present-test
  (testing "Inc counter value when key is present"
    (is (= 2
      (:value (nth (inc-counter-value truth-table data1) 0)))))
)

(deftest inc-counter-value-key-not-present-test
  (testing "Inc counter value when key is not present"
    (is (= [false, false]
      (:key (nth (inc-counter-value truth-table data2) 2)))))
)

(deftest process-counter-test
  (testing "Processing counter"
    (is (= [false, false]
      (:key (:truth-table (nth (:rules (process-counter state data2)) 0)))))
    (is (= {}
      (:truth-table (nth (:rules (process-counter state data2)) 1)))))
  )
