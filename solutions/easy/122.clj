;;;; Solution for 122. Read a binary number




;;Convert a binary number, provided in the form of a string, to its numerical value.



(defn from-binary-string
  [s]
  (reduce #(let [z (* 2 %1)] (if (= %2 \1) (inc z) z)) 0 s)
 )


(def __ from-binary-string )


(= 0     (__ "0"))
(= 7     (__ "111"))
(= 8     (__ "1000"))
(= 9     (__ "1001"))
(= 255   (__ "11111111"))
(= 1365  (__ "10101010101"))
(= 65535 (__ "1111111111111111"))
