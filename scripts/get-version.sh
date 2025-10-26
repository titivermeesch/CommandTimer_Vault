#!/bin/bash

cat build.gradle.kts | grep version | head -n 1 | cut -d"'" -f2
