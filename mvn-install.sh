#!/bin/zsh

RELEASE_VERSION="0.0.1"

cleanup()
{
  echo "cleanup - maven cleanup actions"
  mvn clean
  mvn dependency:purge-local-repository -DmanualInclude="com.example:$RELEASE_VERSION"
  rm -rf ~/.m2/repository/com/example
  echo "cleanup - complete"
}

package()
{
  echo "package jar fle"
  mvn package -DskipTests
  mvn install -DskipTests
  echo "package - complete"
}

deploy()
{
  echo "deploy jar file locally"
  mvn install:install-file -Dfile=target/commons-$RELEASE_VERSION.jar -DgroupId=com.example -DartifactId=commons -Dversion=$RELEASE_VERSION -Dpackaging=jar
  echo "deploy - completed"
}

echo "----------------------------------------"
cleanup

echo "----------------------------------------"
package

echo "----------------------------------------"
deploy

