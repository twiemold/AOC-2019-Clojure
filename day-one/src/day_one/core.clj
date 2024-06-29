(ns day-one.core
  (:require [clojure.math :as math]))


(defn calc-fuel [module]
  (-> (/ module 3) 
      (math/floor) 
      (- 2)
      (int)
      )
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [modules (map parse-long 
                     (line-seq (clojure.java.io/reader "input.txt")))]
    (print "The sum is: " 
           (reduce + (map calc-fuel modules))))
  )

(comment
  (-main)
  (map calc-fuel '(12 1969))
  (reduce + (map calc-fuel '(12 1969)))
  )
