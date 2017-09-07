;;; Solution for 65. Black Box Testing


;; Clojure has many sequence types, which act in subtly different ways. The core functions typically convert them into a uniform "sequence" type and work with them that way, but it can be important to understand the behavioral and performance differences so that you know which kind is appropriate for your application.

;;Write a function which takes a collection and returns one of :map, :set, :list, or :vector - describing the type of collection it was given.
;;You won't be allowed to inspect their class or use the built-in predicates like list? - the point is to poke at them and understand their behavior.

;Special Restrictions class, type, Class, vector?, sequential?, list?, seq?, map?, set?, instance?, getClass

(conj {:1 2 } [:1 2] [:1 4])

(defn black-box-test
   [col]
   (let [el1 [:1 1]
         add1 (conj col el1)
         cnt1 (count add1)
         el2 [:1 2]
         add2 (conj add1 el2)
         cnt2 (count add2)]
      (if (= cnt1 cnt2)
             :map
             (if (= cnt2 (count (conj add2 el2)))
                  :set
                 (if (= el2 (first add2))
                    :list
                    :vector
                  )
              )
      )
   )
 )

;; version using cond
(defn black-box-test2
  [col]
  (let [el1 [:1 1]
         add1 (conj col el1)
         cnt1 (count add1)
         el2 [:1 2]
         add2 (conj add1 el2)
         cnt2 (count add2)]
         (cond
            (= cnt1 cnt2) :map
            (= cnt2 (count (conj add2 el2))) :set
            (= el2 (first add2)) :list
            :else :vecor
         )
   )
)

(def __ black-box-test2)


;;test

(= :map (__ {:a 1, :b 2}))
(= :list (__ (range (rand-int 20))))
(= :vector (__ [1 2 3 4 5 6]))
(= :set (__ #{10 (rand-int 5)}))
(= [:map :set :vector :list] (map __ [{} #{} [] ()]))


