Feature: Check an match
    As Player
    I want to spectate a match

    Scenario:
        Given Seth is null
        When I execute command "/spectate Seth"
        Then I expect Seth to be null