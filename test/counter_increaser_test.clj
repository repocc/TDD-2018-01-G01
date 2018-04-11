(ns counter-increaser-test
  (:require [clojure.test :refer :all]
            [counter-increaser :refer :all]
            [condition-processor :refer :all]
            [state-initializer :refer :all]
            ))

(def truth-table [{:key [true true] :value 1}, {:key [true false] :value 1}, {:key [] :value 1}])

(def parameters1 [true, true])
(def parameters2 [false, false])
(def parameters3 [false, true])
(def parameters4 [])


(def past-data ["spam" true, "spam" true])

(def rules '((define-counter "email-count" []
               true)
             (define-counter "spam-count" []
               (current "spam"))))

(def rules-mapped (map transform-to-state-row rules))

(def state (get-state rules))


(deftest key-is-not-present-test
 (testing "Test if key is present or not in truth table"
    (is (= false
          (key-is-not-present? truth-table parameters1)))
    (is (= true
          (key-is-not-present? truth-table parameters2)))))

(deftest inc-counter-value-key-present-test
  (testing "Inc counter value when key is present"
    (is (= 2
      (:value (nth (inc-counter-value truth-table parameters1) 0))))
    (is (= 2
      (:value (nth (inc-counter-value truth-table parameters4) 2)))))
)

(deftest inc-counter-value-key-not-present-test
  (testing "Inc counter value when key is not present"
    (is (= [false, false]
      (:key (nth (inc-counter-value truth-table parameters2) 3)))))
)

(deftest process-counter-test
  (testing "Processing counter"
    (is (= []
      (:key (:truth-table (nth (:rules (process-counter state {"spam" false, "important" false})) 0)))))
    (is (= {}
      (:truth-table (nth (:rules (process-counter state {"spam" false, "important" false})) 1)))))
  )
