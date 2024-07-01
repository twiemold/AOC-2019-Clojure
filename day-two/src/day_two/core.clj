(ns day-two.core
  (:require [clojure.string :as str]))

(defn opcode-func [code]
  (case code
    1 +
    2 *))

(defn intcode-processor [program]
  (loop [prog program
         curr 0]
    (let [opcode (nth prog curr)
          op-loc-one (nth prog (+ curr 1))
          op-loc-two (nth prog (+ curr 2))
          loc (nth prog (+ curr 3))] 
     (if (= opcode 99)
        prog
        (recur (assoc prog loc ((opcode-func opcode) 
                                (nth prog op-loc-one) 
                                (nth prog op-loc-two))) 
               (+ curr 4))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [grav-prog (vec (map parse-long (str/split (slurp "input.txt") #",")) )]
    (println "Position 0 is" (nth (intcode-processor (assoc grav-prog 1 12 2 2)) 0))))

(comment 
  (-main)
  (intcode-processor (vec '(1,9,10,3,2,3,11,0,99,30,40,50)))
  )
