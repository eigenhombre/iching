(ns iching.core)

(def hexagrams
  (->> "resources/hexagrams.edn"
       slurp
       clojure.edn/read-string))

(defn hexagram [] (rand-nth hexagrams))

(defn as-ascii-block [{elements :elements}]
  (->> elements
       reverse
       (map #(clojure.string/replace % #"-" "▀▀▀▀▀"))
       (map #(clojure.string/replace % #" " "▀   ▀"))
       (clojure.string/join "\n")))

(defn oracle-str []
  (let [{:keys [n
                char
                char-transl
                description
                short-description] :as h} (hexagram)]
    (str (as-ascii-block h)
         (format "\nNumber %s %s (%s)\n%s"
                 n
                 char
                 char-transl
                 description))))

(defn -main [& _]
  (println (oracle-str)))
