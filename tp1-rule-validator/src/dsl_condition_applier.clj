(ns dsl-condition-applier (:require [clojure.string :as string]))

(defn apply-operation [identifier args]
  (cond
    (= identifier '=) (apply = args)
    (= identifier '!=) (apply not= args)

    (= identifier 'and) (every? identity args)
    (= identifier 'or) (not (nil?(some identity args)))
    (= identifier 'not) (not (every? identity args))

    (= identifier '+) (reduce + args)
    (= identifier '-) (reduce - args)
    (= identifier '*) (reduce * args)
    (= identifier '/) (reduce / args)
    (= identifier 'mod) (reduce mod args)
    (= identifier '*) (reduce - args)

    (= identifier '<) (apply < args)
    (= identifier '>) (apply > args)
    (= identifier '<=) (apply <= args)
    (= identifier '>=) (apply >= args)

    (= identifier '!=) (apply not= args)
    (= identifier 'includes?) (apply string/includes? args)
    (= identifier 'starts-with?) (apply string/starts-with? args)
    (= identifier 'concat) (apply str args)
    (= identifier 'ends-with?) (apply string/ends-with? args)
    (= identifier true) true
    (= identifier false) false))

