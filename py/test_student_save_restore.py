import pexpect


def test_student_save_restore():
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
        baza.send("search 2")
        baza.expect(">> 2 | Bozic, Aljaz | 3.2")

        baza.expect("command> ")
        baza.send("search")
        baza.expect("search> First name: ")
        baza.send("Janez")
        baza.expect("search> Last name: ")
        baza.send("Strazisar")
        baza.expect(">> 3 | Strazisar, Janez | 7.3")

        baza.expect("command> ")
        baza.send("save test.bin")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("remove 1")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("remove 2")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("remove 3")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("print")
        baza.expect(">> No. of students: 0")

        baza.expect("command> ")
        baza.send("restore test.bin")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("print")
        baza.expect(">> No. of students: 3")
        baza.expect("	1	| Bizjak, Zan	| 5.3")
        baza.expect("	2	| Bozic, Aljaz	| 3.2")
        baza.expect("	3	| Strazisar, Janez	| 7.3")



        print "PASSED\ttest_student_save_restore"

    except:
        print "FAILED\ttest_student_save_restore"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_student_save_restore()
