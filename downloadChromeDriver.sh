#!/usr/bin/env bash
#Install chromedriver


mkdir -p target
pushd target

version=$(curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE)
echo "Downloading chrome driver version $version"

curl -O https://chromedriver.storage.googleapis.com/$version/chromedriver_mac64.zip
unzip chromedriver_mac64.zip
rm -f /opt/chromedriver
mv chromedriver /opt/

popd
