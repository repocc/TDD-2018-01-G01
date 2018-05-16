(defproject tp1-rule-validator "0.1.0-SNAPSHOT"
  :description "Template for functional rule validation engine"
  :url "http://materias.fi.uba.ar/7510/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.json "0.2.0"]]
  :source-paths ["src/main/clojure"]
  :java-source-paths ["src/main/java"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["resources"]
  :main clojure-java-interop
  :aot :all)
