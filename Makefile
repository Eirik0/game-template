### This is a makefile for compiling the Java project game-template ###


### Helper functions ###

# Recursively find all folders and files in a folder with a given suffix
find_files = $(wildcard $1$2) $(foreach d,$(wildcard $1*),$(call find_files,$d/,$2))
# Find files and folders and then filter by suffix
filter_find = $(filter %$2,$(call find_files,$1,*$2))


### Configuration for sources file and compilation artefacts ###

# The name of the jar file to create
JAR_FILE := game-template.jar

# The directory containing the `.java` files
SOURCE_DIR := src
# The directory to put the `.class` files
CLASS_DIR := bin

# The `.java` files to compile
SOURCE_FILES := $(call filter_find,$(SOURCE_DIR),.java)
# The `.class` created when compiling
CLASS_FILES := $(SOURCE_FILES:$(SOURCE_DIR)/%.java=$(CLASS_DIR)/%.class)


### Default rule ###
all: $(JAR_FILE)


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
# Define phony targets
.PHONY: all clean cleanall


### Rules ###

### Make the directory to hold the compiled class files ###
$(CLASS_DIR):
	mkdir $(CLASS_DIR)

### Compile `.java` files to `.class` files ###
# Java compiler
JAVAC := javac
# -cp - Specify classpath
# -d  - Set output directory
# -g  - Generate debugging information
JAVACFLAGS := \
	-cp $(SOURCE_DIR) \
	-d $(CLASS_DIR) \
	-g:none
# javac [ options ] [ sourcefiles ] [ classes ] [ @argfiles ]
$(CLASS_DIR)/%.class: $(SOURCE_DIR)/%.java | $(CLASS_DIR)
	$(JAVAC) $(JAVACFLAGS) $<

### Copy `.class` files to the `.jar` archive ###
# Java archiving and compression tool
JAR := jar
# c - Create a new archive
# e - Set the application entry point
# f - Specify the file name
# v - Verbose output
JARFLAGS := cf
# jar c[efmMnv0] [entrypoint] [jarfile] [manifest] [-C dir] file ... [-Joption ...] [@arg-file ...]
$(JAR_FILE): $(CLASS_FILES)
	$(JAR) $(JARFLAGS) $@ -C $(CLASS_DIR) .

### Remove the jar archive and the class files ###
clean:
	$(RM) $(JAR_FILE) $(CLASS_FILES)
	
### Remove the jar archive and all class files ###
cleanall:
	rm -rf $(JAR_FILE) $(CLASS_DIR)
