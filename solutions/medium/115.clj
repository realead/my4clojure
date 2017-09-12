;;;; Solution 115. The Balance of N


;;; A balanced number is one whose component digits have the same sum on the left and right halves of the number. Write a function which accepts an integer n, and returns true iff n is balanced.

;tests

(defn balanced?
   ([n]
   (let [lst ((fn [n col] (if (= 0 n) col (recur (quot n 10) (conj col (mod n 10))))) n [])]
     (balanced? lst 0 0)
   ))
   ([lst left right]
     (if (< (count lst) 2)
         (= left right)
         (recur (rest (butlast lst)) (+ left (first lst)) (+ right (last lst)))
      )
   )
)


(def __ balanced?)

;tests

(= true (__ 11))
(= true (__ 121))
(= false (__ 123))
(= true (__ 0))
(= false (__ 88099))
(= true (__ 89098))
(= true (__ 89089))
(= (take 20 (filter __ (range)))
   [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])
