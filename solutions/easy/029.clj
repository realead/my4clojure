;;; Solution for 29. Get the Caps


;; Write a function which takes a string and returns a new string containing only the capital letters.

(defn get-the-caps
  [s]
  (apply str
         (filter #(Character/isUpperCase %) s)
  )
)

(def __ get-the-caps)

; tests

(= (__ "HeLlO, WoRlD!") "HLOWRD")
(empty? (__ "nothing"))
(= (__ "$#A(*&987Zf") "AZ")
