;;;; Solution for 69. Merge with a Function


;; Write a function which takes a function f and a variable number of maps. Your function should return a map that consists of the rest of the maps conj-ed onto the first.
;; If a key occurs in more than one map, the mapping(s) from the latter (left-to-right) should be combined with the mapping in the result by calling (f val-in-result val-in-latter)


;; special restriction: merge-with



(defn my-merge-with
  [fun & maps]
  (let [res (first maps)
        maps (rest maps)]
       (reduce (fn [res map]
                   (into res (for [[key val] map]
                                  (if (contains? res key)
                                      [key (fun (res key) val)]  ; combine
                                      [key val])                 ; just take
                               )
                   )
               ) res maps)
  )
)

(def __ my-merge-with)


;;tests
(= (__ * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})


(= (__ - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})

(= (__ concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]})
