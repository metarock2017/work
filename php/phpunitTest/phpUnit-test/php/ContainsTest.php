<?php
use PHPUnit\Framework\TestCase;

class ContainsTest extends TestCase
{
	public function testFailure()
	{
		$this->assertContains(4, [1, 2, 3]);
	}
} 