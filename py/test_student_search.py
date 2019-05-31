import pexpect


def test_student_serach():
    baza = pexpect.pexpect()

    try:
        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("63170001")
        baza.expect("add> First name: ")
        baza.send("Zan")
        baza.expect("add> Last name: ")
        baza.send("Bizjak")
        baza.expect("add> Avg. grade: ")
        baza.send("6.3")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("63170002")
        baza.expect("add> First name: ")
        baza.send("Aljaz")
        baza.expect("add> Last name: ")
        baza.send("Bozic")
        baza.expect("add> Avg. grade: ")
        baza.send("6.2")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("63170003")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Strazisar")
        baza.expect("add> Avg. grade: ")
        baza.send("7.3")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("search 63170002")
        baza.expect(">> \t63170002\t| Bozic, Aljaz\t| 6.2")

        baza.expect("command> ")
        baza.send("search")
        baza.expect("search> First name: ")
        baza.send("Janez")
        baza.expect("search> Last name: ")
        baza.send("Strazisar")
        baza.expect(">> \t63170003\t| Strazisar, Janez\t| 7.3")

        baza.expect("command> ")
        baza.send("remove 63170001")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("remove 63170002")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("remove 63170003")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("print")
        baza.expect(">> No. of students: 0")


        print "PASSED\ttest_student_search"

    except:
        print "FAILED\ttest_student_search"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_student_serach()
