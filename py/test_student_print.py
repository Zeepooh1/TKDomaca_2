import pexpect


def test_student_print():
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
        baza.send("count")
        baza.expect(">> No. of students: 1")

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
        baza.send("count")
        baza.expect(">> No. of students: 2")

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
        baza.send("print")
        baza.expect(">> No. of students: 3")
        baza.expect("	63170001	| Bizjak, Zan	| 6.3")
        baza.expect("	63170002	| Bozic, Aljaz	| 6.2")
        baza.expect("	63170003	| Strazisar, Janez	| 7.3")

        print "PASSED\ttest_student_print"

    except:
        print "FAILED\ttest_student_print"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_student_print()
