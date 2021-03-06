### This file defines common elements intended to be reused in this and other Java project's makefiles ###


### Configuration for make ###

# Set shell
SHELL = /bin/sh
# Suppress the makefile rule
MAKEFILE: ;
# Suppress predefined rules
MAKEFLAGS += --no-builtin-rules
# Clear and reset suffixes
.SUFFIXES:
.SUFFIXES: .java .class


### Define OS dependent variables ###

# Extension for scripts
SCRIPT_EXTENSION := sh
# Separator character for classpath entries
CP_SEPARATOR := :
# Command to recursively delete
RM := rm -rf
# Redefine for windows
ifeq ($(OS),Windows_NT)
	SCRIPT_EXTENSION := bat
	CP_SEPARATOR := ;
	# RM := del /S /Q /F
endif


### Helper functions ###

# Recursively find all folders and files in a folder with a given suffix
find_files = $(wildcard $1$2) $(foreach d,$(wildcard $1*),$(call find_files,$d/,$2))
# Find files and folders and then filter by suffix
filter_find = $(filter %$2,$(call find_files,$1,*$2))
# Find files and folders and then attempt to filter out folders
filter_findall = $(filter-out %/,$(call find_files,$1,*))


### Configuration for sources file and compilation artefacts ###

# The directory containing the `.java` files
SOURCE_DIR := src
# The directory to put the `.class` files
CLASS_DIR := bin

# The `.java` files to compile
SOURCE_FILES := $(call filter_find,$(SOURCE_DIR),.java)
# The `.class` created when compiling
CLASS_FILES := $(SOURCE_FILES:$(SOURCE_DIR)/%.java=$(CLASS_DIR)/%.class)


### Java tools ###

# Java compiler
JAVAC := javac
# Java archiving and compression tool
JAR := jar
# Java launcher
JAVA := java


### Rules ###

### Make the directory to hold the compiled class files ###
$(CLASS_DIR):
	mkdir $(CLASS_DIR)
