(ns condition-processor-test
  (:require [clojure.test :refer :all]
            [condition-processor :refer :all]))

;;todo completar tests.

(def condition1 '(and (!= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))
(def condition2 '(or (= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))
(def condition3 '(and (= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))
(def condition4 '(and (= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))

(def data1 {"sender" "ramona" "receiver" "jorge" "subject" "holo"})
(def data2 {"sender" "culkin" "receiver" "ramona" "subject" "holo"})
(def data3 {"sender" "ramona" "receiver" "scott" "subject" "carta importante"})
(def data4 {"sender" "ramona" "receiver" "scott" "subject" "holo"})

(def data-actual {"miau" false})
(def data-match {"sender" "ramona" "receiver" "scott" "subject" "holo"})
(def past-data [data1 data2 data3])

(deftest pass-condition-test
  (testing "Should pass condition given data"
    (is (= false
           (pass? condition1 data1 data2)))
    (is (= true
           (pass? condition1 data1 data4)))
    (is (= false
            (pass? condition1 data1 data2)))
    (is (= false
            (pass? condition1 data1 data2)))))


(deftest pass-condition-test
         (testing "Should pass condition given data"
                  (is (= false
                         (pass-condition? condition1 data-actual past-data)))
                  (is (= true
                         (pass-condition? condition1 data-match past-data)))
                  ))



