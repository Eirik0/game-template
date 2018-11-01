### This is a makefile for compiling the Java project game-template ###


# Dependencies
include common.mk

### Configuration for sources file and compilation artefacts ###

# The name of the jar file to create
JAR_FILE := game-template.jar


### Rules ###

# Define phony targets
.PHONY: all clean cleanall

### Default rule ###
.DEFAULT_GOAL := all
all: $(JAR_FILE)

### Compile `.java` files to `.class` files ###
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
