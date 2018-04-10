(ns condition-processor-test
  (:require [clojure.test :refer :all]
            [signals-processor :refer :all]))

(def state {:rules '({:type "counter",
                      :name "email-count",
                      :params [],
                      :condition true,
                      :truth-table ({:key [], :value 8})}
                     {:type "counter",
                      :name "spam-count",
                      :params [],
                      :condition (current "spam"),
                      :truth-table ({:key [], :value 40})}
                     {:type "signal",
                      :name "spam-fraction",
                      :operation (/ (counter-value "spam-count" []) (counter-value "email-count" []))
                      :condition (true)}
                     )
            :past-data [{}]})

(def true-condition '(true))

(def condition5 '(or (< (current "n") (past "n")) (= (mod (current "n") 1) 1)))

(def data1 {"spam" true, "receiver" "jorge", "subject" "holo"})
(def data2 {"spam" "culkin", "receiver" "ramona", "subject" "holo"})
(def data3 {"spam" "ramona", "receiver" "scott", "subject" "carta importante"})
(def data4 {"spam" "juan", "receiver" "jorge", "subject" "holo"})

(def data-actual {"miau" false})
(def data-match {"sender" "ramona", "receiver" "scott", "subject" "holo"})

(def past-data [data1 data2 data3])

(def dataWithNumbers1 {"n" 10})
(def dataWithNumbers2 {"n" 11})

(deftest pass-condition-test
  (testing "Should pass condition given data"
           (is (= false
                  (process-signal condition1 data1 data2)))
           ))



(deftest process-signals-test
  (testing "Should return signal data"
           (is (= false
                  (process-signal state data-actual past-data)))
           (is (= true
                  (pass-condition? condition4 data-match past-data)))
           (is (= true
                  (pass-condition? true-condition data-match past-data)))
           ))



