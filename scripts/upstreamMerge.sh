#!/usr/bin/env bash

(
set -e
PS1="$"
basedir="$(cd "$1" && pwd -P)"
workdir="$basedir"

function update {
    cd "$workdir/$1"
    git fetch && git reset --hard origin/master
    cd ../
    git add $1
}

update Rainbow

if [[ "$2" = "dev" ]] ; then
	update Rails
fi
)
