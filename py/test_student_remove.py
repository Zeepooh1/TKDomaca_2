import pexpect


def test_sk_print():
    baza = pexpect.pexpect()

    try:
        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Zan")
        baza.expect("add> Last name: ")
        baza.send("Bizjak")
        baza.expect("add> Avg. grade: ")
        baza.send("5.3")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("2")
        baza.expect("add> First name: ")
        baza.send("Aljaz")
        baza.expect("add> Last name: ")
        baza.send("Bozic")
        baza.expect("add> Avg. grade: ")
        baza.send("3.2")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("3")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Strazisar")
        baza.expect("add> Avg. grade: ")
        baza.send("7.3")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("remove 2")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("search 2")
        baza.expect(">> Student does not exists")

        baza.expect("command> ")
        baza.send("remove")
        baza.expect("remove> First name: ")
        baza.send("Janez")
        baza.expect("remove> Last name: ")
        baza.send("Strazisar")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("search")
        baza.expect("search> First name: ")
        baza.send("Janez")
        baza.expect("search> Last name: ")
        baza.send("Strazisar")
        baza.expect(">> Student does not exists")


        print "PASSED\ttest_student_search"

    except:
        print "FAILED\ttest_student_search"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_sk_print()
