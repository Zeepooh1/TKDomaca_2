import pexpect


def test_student_count():
    baza = pexpect.pexpect()

    try:
        baza.expect("command> ")
        baza.send("count")
        baza.expect(">> No. of students: 0")

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
        baza.send("count")
        baza.expect(">> No. of students: 1")

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
        baza.send("count")
        baza.expect(">> No. of students: 2")

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
        baza.send("count")
        baza.expect(">> No. of students: 3")

        baza.expect("command> ")
        baza.send("remove 2")
        baza.expect(">> OK")

        baza.expect("command> ")
        baza.send("count")
        baza.expect(">> No. of students: 2")

        baza.expect("command> ")
        


        print "PASSED\ttest_student_count"

    except:
        print "FAILED\ttest_student_count"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_student_count()
