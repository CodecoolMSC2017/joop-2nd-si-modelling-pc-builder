# joop-2nd-si-modelling-pc-builder

## `Compilation`
    cd <repo>
    cd src
    javac com/codecool/*.java

## `Execution`
    cd <repo>
    cd src
    java com.codecool.Main

## `Description`

    This program will allow the user to build custom PCs and use them
    for different task to make progress in the game.

## `Main menu commands`

### `:home`
    This is where you can use your PCs.
### `:store`
    Enters the store menu where you can browse and buy components.
### `:build`
    Modify a PC you have already built or build a brand new one.
### `:find`
    Select one of your PCs to display it's specs.
### `:inventory`
    Displays the components you have bought but have not built in yet.
### `:save`
    Saves your progress (saved game is loaded automatiaclly upon startup).
### `:help`
    Displays this helpful description.
### `:exit`
    After asking to save the game exits the program.

## `Progress`

### `Week 1`
    Started the project, created class hierarchy. The program has many
    functional features, Store, Inventory and Find are completely
    functional. Started implementing Build, new computers can be created,
    parts can be built in but compatibility is not checked yet.

    Plans for next week:
    Make parts removable from computers
    Add disassemble option for computers
    Compatibilty check when adding parts to computers
    Saving/loading to/from files
