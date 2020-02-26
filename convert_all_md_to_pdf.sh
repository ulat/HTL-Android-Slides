#!/bin/bash

find ./ -iname "*.md" -type f -exec sh -c 'pandoc "${0}" -o "dist/${0%.md}.pdf"' {} \;
