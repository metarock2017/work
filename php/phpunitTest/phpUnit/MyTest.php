<?php
use PHPUnit\Framework\TestCase;

class MyTest extends TestCase
{
	/**
	 * @group one
	 */
	public function testFailure()
	{
		$this->assertContains(4, [1, 2, 3]);
	}

	/**
	 * @group two
	 */
	public function testPushAndPop()
	{
		$stack = [];
		$this->assertEquals(0, count($stack));

        array_push($stack, 'foo');
		$this->assertEquals('foo', $stack[count($stack)-1]);
		$this->assertEquals(1, count($stack));

		$this->assertEquals('foo', array_pop($stack));
		$this->assertEquals(0, count($stack));
	}
} 